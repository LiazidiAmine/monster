package tp3_equilibrium;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import tp3_equilibrium.parsing.LineParser;

public class Player {
	private final String name;
	private final List<Action> actions;
	private int health;

	private Player(String name, List<Action> monsters) {
		this.name = requireNonNull(name);
		this.health = 5;
		this.actions = monsters;
	}

	public static Player create(String name, Path path, ActionFactoryKit kit) throws IOException{
		List<Action> actions = LineParser.parse(path, kit::create);
		return new Player(name, actions);
	}

	public boolean isDead(){
		return health <= 0;
	}

	public void beingHit(int damage) {
		health -= damage;
	}

	public String getName(){
		return name;
	}

	public Iterator<Action> action(){
		return Collections.unmodifiableList(actions).iterator();
	}
}
