package springcourse.dao;

import org.springframework.stereotype.Component;
import springcourse.model.Person;

import java.util.ArrayList;
import java.util.List;

//@Component//это связывает
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = -1;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT, "Katy"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.get(id);
       // people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);//в бд автоинкремент id. Здесь мы делаем только потому
        // что у нас коллекция
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatePerson.getName());
    }

    public void delete(int i) {
        people.remove(i);
    }
}
