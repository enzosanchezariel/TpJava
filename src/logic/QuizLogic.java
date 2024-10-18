package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Question;

public class QuizLogic {
	private List<Question> questions;

    public QuizLogic() {
        questions = new ArrayList<>();
 
        questions.add(new Question("¿Cuál es la capital de Francia?", Arrays.asList("Madrid", "Londres", "París", "Berlín")));
        questions.add(new Question("¿Cuál es el lenguaje de programación más popular?", Arrays.asList("Java", "Python", "C++", "JavaScript")));
        questions.add(new Question("¿En qué año terminó la Segunda Guerra Mundial?", Arrays.asList("1939", "1945", "1918", "1965")));
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
