package springcourse.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springcourse.dao.PersonDAO;
import springcourse.model.Person;

@Controller
@RequestMapping("/people")//вход в программу
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")    //то что мы запишем в id появится в аргументах метода
    public String show(@PathVariable("id") int id, Model model) {
        //получаем по id и создаем модель на основе его
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        //2 вида либо так, либо как в коментах
//    public String newPerson(Model model) {
//        model.addAttribute("person", new Person()); //"person" это ключ
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";//redirect это переход на другую страницу
    }

    @GetMapping("/{id}/edit")//гет запрос
    public String edit(Model model, @PathVariable("id") int id) {//id везде, потому что мы
        // извлекаем его из id в браузере и ложим в аргумент метода
        //метод возвращает страницу измененного человека
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}") //метод так называется, только потому что у метода другой запрос patch
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";  //есть forward и redirect из задачи MVC(один идет в другую страницу,
                                    // а второй вроде возвращает
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}