package edu.douzone.bitc.modernjava.chapter11;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        String carInsuranceName = main.getCarInsuranceName(new Person());

        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
//        Optional<String> name =
//            optPerson.map(Person::getCar)
//                .map(Car::getInsurance)
//                .map(Insurance::getName);

        String result = optPerson.flatMap(Person::getCar)
            .flatMap(Car::getInsurance)
            .map(Insurance::getName)
            .orElse("Unknow");

        System.out.println(result);


    }

//    public String getCarInsuranceName(Person person) {
//        return person.getCar().getInsurance().getName();
//    }

    // 외부 DSL 어따 쓰는건지 알려주세요!


//    public String getCarInsuranceNameOptional(Optional<Person> person) {
//        return person.flatMap(Person::getCar)
//            .flatMap(Car::getInsurance)
//            .map(Insurance::getName)
//            .orElse("Unknown");
//    }

    public Set<String> getCarInsuranceNamesStream(List<Person> persons) {
        return persons.stream()
            .map(Person::getCar)    // 사람 목록을 각 사람이 보유한 자동차의 Optional<Car> 스트림으로 변환
            .map(optCar -> optCar.flatMap(Car::getInsurance))   // FlatMap 연산을 이용해 Optional<Car> 을 해당 Optional<Insurance> 로 변환
            .map(optIns -> optIns.map(Insurance::getName))  // Optional<Insurance> 를 해당 이름의 Optional<String> 으로 매핑
            .flatMap(Optional::stream)  // Stream<Optional<String>>을 현재 이름을 포함하는 Stream<String> 으로 변환
            .collect(Collectors.toSet());   // 결과 문자열을 중복되지 않은 값을 갖도록 집합으로 수집
    }

    public Set<String> getCarInsuranceNamesOptPersonList(List<Optional<Person>> persons) {
        return persons.stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(Person::getCar)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(Car::getInsurance)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(Insurance::getName)
            .collect(Collectors.toSet());
    }
}
