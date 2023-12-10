package me.fishydarwin.openrp.core;

/**
 * Represents dice that can be used in rolling.
 */
public interface IORPDice {

    /**
     * Returns the minimum amount that can be rolled on this dice.
     * @return the minimum
     */
    int getMinimum();

    /**
     * Returns the maximum amount that can be rolled on this dice.
     * @return the maximum
     */
    int getMaximum();

    /**
     * Returns the bonus applied to the dice roll, or 0 if there is no bonus.
     * @return the bonus, or 0 if none
     */
    int getBonus();

    /**
     * Returns the malus (opposite of bonus) applied to the dice roll, or 0 if there is no malus.
     * @return the malus, or 0 if none
     */
    int getMalus();

    /**
     * Generates a random number using the parameters of this dice, effectively rolling it.
     * @return r = rand(min, max) + bonus - malus
     */
    int roll();

}
