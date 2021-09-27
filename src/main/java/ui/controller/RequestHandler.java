package ui.controller;

import domain.service.AnimalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected AnimalService service;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response);

    public AnimalService getService() {
        return service;
    }

    public void setService(AnimalService service) {
        this.service = service;
    }
}
