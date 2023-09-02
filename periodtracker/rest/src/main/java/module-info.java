module periodtracker.rest {

	requires transitive periodtracker.core;
	requires transitive periodtracker.storage;
	requires transitive spring.web;
	requires spring.beans;
	requires transitive spring.boot;
	requires spring.context;
	requires spring.boot.autoconfigure;
	requires javafx.graphics;
	opens periodtracker.rest;
	exports periodtracker.rest;

}


