package ui.controller;

import domain.model.Animal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Add extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<String>();
        Animal animal = new Animal();
        setName(animal, request, errors);
        setType(animal, request, errors);
        setFood(animal, request, errors);
        if (errors.size() == 0) {
            try {
                service.add(animal);
                HttpSession session = request.getSession();
                session.setAttribute("lastAddedAnimal", animal);
                return "Controller?command=Overview";
            }
            catch (IllegalArgumentException exc) {
                request.setAttribute("error", exc.getMessage());
                return "add.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "add.jsp";
        }
    }

    private void setName(Animal animal, HttpServletRequest request, ArrayList<String> errors) {
        String name = request.getParameter("name");
        try {
            animal.setName(name);
            request.setAttribute("nameClass", "has-success");
            request.setAttribute("namePreviousValue", name);
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            request.setAttribute("nameClass", "has-error");
        }
    }

    private void setType(Animal animal, HttpServletRequest request, ArrayList<String> errors) {
        String type = request.getParameter("type");
        try {
            animal.setType(type);
            request.setAttribute("typeClass", "has-success");
            request.setAttribute("typePreviousValue", type);
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            request.setAttribute("typeClass", "has-error");
        }
    }

    private void setFood(Animal animal, HttpServletRequest request, ArrayList<String> errors) {
        int food;
        if(request.getParameter("food").isBlank()){
            food = -1;
        }else{
            food = Integer.parseInt(request.getParameter("food"));
        }
        try {
            animal.setFood(food);
            request.setAttribute("foodClass", "has-success");
            request.setAttribute("foodPreviousValue", food);
        }
        catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
            request.setAttribute("foodClass", "has-error");
        }
    }
}
