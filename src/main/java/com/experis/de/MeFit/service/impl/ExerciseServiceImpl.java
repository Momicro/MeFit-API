package com.experis.de.MeFit.service.impl;

import com.experis.de.MeFit.models.Exercise;
import com.experis.de.MeFit.models.Workout;
import com.experis.de.MeFit.repositories.ExerciseRepository;
import com.experis.de.MeFit.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;

public class ExerciseServiceImpl implements ExerciseService {

    //Get the JPA Services
    @Autowired
    ExerciseRepository exerciseRepository;

    //@Autowired
    //MuscleGroupRepository muscleGroupRepository;

    @Override
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise updateExerciseMuscleGroups(int[] muscleGroupIds, String exerciseId) {

        Exercise exercise = exerciseRepository.getById(Long.parseLong(exerciseId));

        return exerciseRepository.save(exercise);
    }

    //Function to delete the exercise with nested data inside.
    @Override
    public void deleteExercise(Exercise exercise) {

        Workout workout = exercise.getWorkout();
        workout.getExercises().remove(exercise);

        exerciseRepository.delete(exercise);
    }
}
