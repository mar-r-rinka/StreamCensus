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
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних :" + count);

        List<String> conscript = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() < 27 && x.getAge() >= 18)
                .map(Person::getFamily)
                .limit(20)
                .collect(Collectors.toList());
        System.out.println("Призывники: " + conscript);


        List<Person> workableMen = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() < 65 && x.getAge() >= 18 && x.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .limit(50)
                .collect(Collectors.toList());
        List<Person> workableWomen = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() < 60 && x.getAge() >= 18 && x.getSex() == Sex.WOMAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .limit(50)
                .collect(Collectors.toList());
        List<Person> workable = new ArrayList<>();
        workable.addAll(workableMen);
        workable.addAll(workableWomen);

        System.out.println("Потенциально работоспособные люди с высшим образованием: " + workable);
    }


}