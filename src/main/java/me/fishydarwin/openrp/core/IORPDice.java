/*
 *    Copyright 2024 The OpenRP Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
