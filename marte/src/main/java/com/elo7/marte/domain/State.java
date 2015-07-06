package com.elo7.marte.domain;

public interface State {
    Coordinates move(Coordinates coordinates);
    boolean canMove(Coordinates plateauCoordinates, Coordinates coordinates);
}

