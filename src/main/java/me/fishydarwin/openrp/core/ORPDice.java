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

import com.google.common.base.Preconditions;

import java.util.concurrent.ThreadLocalRandom;

public class ORPDice implements IORPDice {
    private final int minimum;
    private final int maximum;

    private final int bonus;
    private final int malus;

    public ORPDice(int minimum, int maximum, int bonus, int malus) {
        Preconditions.checkArgument(minimum > maximum, "Min cannot be greater than max");
        this.minimum = minimum;
        this.maximum = maximum;
        this.bonus = bonus;
        this.malus = malus;
    }

    @Override
    public int getMinimum() {
        return minimum;
    }

    @Override
    public int getMaximum() {
        return maximum;
    }

    @Override
    public int getBonus() {
        return bonus;
    }

    @Override
    public int getMalus() {
        return malus;
    }

    @Override
    public int roll() {
        return ThreadLocalRandom.current().nextInt(minimum, maximum) + bonus - malus;
    }
}
