package com.ModuleConnectionAlgorithm.SomeAlgorithms.basicGenetic;


import com.ModuleConnectionAlgorithm.EasyExample.ShirtGeneticAlgorithm;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class SpeciesModulePopulation {

    private List<SpeciesModuleIndividual> speciesNum;

    SpeciesModulePopulation() {
        speciesNum = new ArrayList<>(ShirtTestData.POPULATION_SIZE);
    }

    public void add(SpeciesModuleIndividual individual) {
        speciesNum.add(individual);
    }

    public SpeciesModuleIndividual getFittestIndividual() {
        SpeciesModuleIndividual fittest = speciesNum.get(0);
        fittest.calFitness();
        for (int i = 1; i < speciesNum.size(); i++) {
            speciesNum.get(i).calFitness();
            if (speciesNum.get(i).getFitness().compareTo(fittest.getFitness()) < 0) {
                fittest = speciesNum.get(i);
            }
        }
        return fittest;
    }
}
