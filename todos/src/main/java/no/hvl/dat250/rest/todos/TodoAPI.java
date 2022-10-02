package no.hvl.dat250.rest.todos;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.Gson;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {

	private static ArrayList<Todo> todos;

	public static void main(String[] args) {
		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		todos = new ArrayList<Todo>();

		after((req, res) -> res.type("application/json"));

		// TODO: Implement API, such that the testcases succeed.
		get("/todos", (req, res) -> {
			if (todos.size() == 1)
				return new Gson().toJson(todos.get(0));
			else return new Gson().toJson(todos);
		});
		
		get("/todos/:id", (req, res) -> {
			try {
				Long id = Long.parseLong(req.params(":id"));

				for (int i = 0; i < todos.size(); i++) {
					if (todos.get(i).getId() == id) {
						return new Gson().toJson(todos.get(i));
					}
				}
				return String.format("Todo with the id \"%s\" not found!", req.params(":id"));
				
			} catch (NumberFormatException e) {
				return String.format("The id \"%s\" is not a number!", req.params(":id"));
			}
		});
		
		post("/todos", (req, res) -> {
			Todo todo = new Gson().fromJson(req.body(), Todo.class);
			todo.setId(Long.valueOf(todos.size()));
			todos.add(todo);
			return todo.toJson();
		});

		put("/todos/:id", (req, res) -> {

			try {
				Long id = Long.parseLong(req.params(":id"));

				for (int i = 0; i < todos.size(); i++) {
					if (todos.get(i).getId() == id) {
						Todo todo = new Gson().fromJson(req.body(), Todo.class);
						todo.setId(id);
						todos.set(i, todo);
						return todos.get(i).toJson();
					}
				}
				return null;
			} catch (NumberFormatException e) {
				return String.format("The id \"%s\" is not a number!", req.params(":id"));
			}

		});

		delete("/todos/:id", (req, res) -> {
			try {
				Long id = Long.parseLong(req.params(":id"));
				for (int i = 0; i < todos.size(); i++) {
					if (todos.get(i).getId() == id) {
						todos.remove(i);
						return true;
					}
				}
				return String.format("Todo with the id \"%s\" not found!", req.params(":id"));
			} catch (NumberFormatException e) {
				return String.format("The id \"%s\" is not a number!", req.params(":id"));
			}
		});
	}
}
