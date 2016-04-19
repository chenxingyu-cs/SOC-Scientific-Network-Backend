package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Added for testing: show the index page
 */
public class Application extends Controller {

    public Result index() {
        return ok(views.html.index.render("Hello"));
    }

}
