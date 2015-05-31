package com.szogibalu.mentoring.multithreading;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ChainSmokerProblem {

    static final Random RANDOM = new Random();

    enum Ingredient {
	MATCHES, PAPER, TOBACCO;
    }

    static abstract class Smoker implements Runnable {

	private final Desktop desktop;

	public Smoker(Desktop desktop) {
	    this.desktop = desktop;
	}

	abstract Ingredient getIngredient();

	void smoke() {
	    System.out.println(getIngredient() + " smoker is smoking...");
	}

	@Override
	public void run() {
	    while (true) {
		synchronized (desktop) {
		    while (!desktop.isReadyForSmoke()) {
			try {
			    desktop.wait();
			} catch (final InterruptedException e) {
			    Thread.currentThread().interrupt();
			}
		    }
		    if (!desktop.getIngredients().contains(getIngredient())) {
			smoke();
			desktop.getIngredients().clear();
			try {
			    Thread.sleep(RANDOM.nextInt(5000));
			} catch (final InterruptedException e) {
			    Thread.currentThread().interrupt();
			}
		    }
		    desktop.notifyAll();
		}
	    }
	}
    }

    static class TobaccoSmoker extends Smoker {

	public TobaccoSmoker(Desktop desktop) {
	    super(desktop);
	}

	@Override
	public Ingredient getIngredient() {
	    return Ingredient.TOBACCO;
	}
    }

    static class PaperSmoker extends Smoker {

	public PaperSmoker(Desktop desktop) {
	    super(desktop);
	}

	@Override
	public Ingredient getIngredient() {
	    return Ingredient.PAPER;
	}
    }

    static class MatchesSmoker extends Smoker {

	public MatchesSmoker(Desktop desktop) {
	    super(desktop);
	}

	@Override
	public Ingredient getIngredient() {
	    return Ingredient.MATCHES;
	}
    }

    static class Coordinator implements Runnable {

	private final Desktop desktop;

	public Coordinator(Desktop desktop) {
	    this.desktop = desktop;
	}

	@Override
	public void run() {
	    while (true) {
		synchronized (desktop) {
		    while (desktop.isReadyForSmoke()) {
			try {
			    desktop.wait();
			} catch (final InterruptedException e) {
			    Thread.currentThread().interrupt();
			}
		    }
		    final Set<Ingredient> selectedItems = new HashSet<>();
		    while (selectedItems.size() != 2) {
			selectedItems.add(desktop.getRandomSmoker()
				.getIngredient());
		    }
		    System.out.println("Selected items: " + selectedItems);
		    desktop.getIngredients().addAll(selectedItems);
		    desktop.notifyAll();
		}
	    }

	}
    }

    static class Desktop {

	private final List<Ingredient> ingredients;

	private List<Smoker> smokers;

	public Desktop() {
	    ingredients = new ArrayList<>();
	}

	public void addSmokers(Smoker... smokers) {
	    this.smokers = asList(smokers);
	}

	public List<Ingredient> getIngredients() {
	    return ingredients;
	}

	public Smoker getRandomSmoker() {
	    return smokers.get(RANDOM.nextInt(smokers.size()));
	}

	public boolean isReadyForSmoke() {
	    if (smokers == null) {
		return false;
	    }
	    return ingredients.size() == smokers.size() - 1;
	}

	public List<Smoker> getSmokers() {
	    return smokers;
	}
    }

    public static void main(String[] args) {
	final Desktop desktop = new Desktop();
	final TobaccoSmoker tobaccoSmoker = new TobaccoSmoker(desktop);
	final PaperSmoker paperSmoker = new PaperSmoker(desktop);
	final MatchesSmoker matchesSmoker = new MatchesSmoker(desktop);

	desktop.addSmokers(tobaccoSmoker, paperSmoker, matchesSmoker);

	final Coordinator coordinator = new Coordinator(desktop);

	new Thread(coordinator).start();

	new Thread(tobaccoSmoker).start();
	new Thread(paperSmoker).start();
	new Thread(matchesSmoker).start();
    }
}
