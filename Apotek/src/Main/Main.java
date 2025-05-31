package main;

import controller.TransaksiController;
import model.TransaksiModel;
import view.TransaksiView;

public class Main {
    public static void main(String[] args) {
        TransaksiView view = new TransaksiView();
        TransaksiModel model = new TransaksiModel();
        new TransaksiController(model, view);
    }
}