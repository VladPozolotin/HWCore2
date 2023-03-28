import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long nonAdults = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        List<String> mobiks = persons.stream()
                .filter(m -> m.getSex() == Sex.MAN)
                .filter(m -> m.getAge() >= 18 && m.getAge() <= 27)
                .map(m -> m.getFamily() + "")
                .collect(Collectors.toList());
        List<Person> sortedWorkers = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> ((p.getSex() == Sex.MAN) && (p.getAge() <= 60)) || ((p.getSex() == Sex.WOMAN) && (p.getAge() <= 65)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}