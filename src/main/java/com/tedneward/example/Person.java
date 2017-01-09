package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;

  private static int personCount;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    personCount++;
  }

  public void setAge(int value) {
    if (value < 0) { 
      throw new IllegalArgumentException("You can't be under 0 years old!!");
    }
    int old = age;
    age = value;

    this.pcs.firePropertyChange("age", old, value);
    propertyChangeFired = true;
  }

  public int getAge() {
    return age;
  }

  public void setName(String value) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("Need a name!");
    }
    String old = name;
    name = value;

    this.pcs.firePropertyChange("name", old, value);
    propertyChangeFired = true;
  }

  public String getName() {
    return name;
  }

  public void setSalary(double value) {
    if (value < 0) {
      throw new IllegalArgumentException("You can't have a negative salary!");
    }
    double old = salary;
    salary = value;

    this.pcs.firePropertyChange("salary", old, value);
    propertyChangeFired = true;
  }

  public double getSalary() {
    return salary;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public String getSSN() {
    return ssn;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";
  }

  public int count() {
    return personCount;
  }

  // Returns true if two person instances have the same name and age
  public boolean equals(Object obj) {
    // return true if a person is compared with itself
    if (this == obj) {
      return true;
    }
    // check if it is indeed a Person object
    if (!(obj instanceof Person)) {
      return false;
    }
    Person person = (Person) obj;
    return (this.name.equals(person.name)) && (this.age == person.age);
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static ArrayList<Person> getNewardFamily() {
    Person ted = new Person("Ted", 41, 250000);
    Person charlotte = new Person("Charlotte", 43, 150000);
    Person mike = new Person("Michael", 22, 10000);
    Person matt = new Person("Matthew", 15, 0);
    ArrayList<Person> list = new ArrayList<Person>();
    list.add(ted);
    list.add(charlotte);
    list.add(mike);
    list.add(matt);
    return list; 
  }

  public int compareTo(Person p) {
    double diff = p.salary - salary;
    return (int) diff; 
  }

  static class AgeComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
      Person p1 = (Person) o1;
      Person p2 = (Person) o2;
      if (p1.age == p2.age) {
        return 0;
      } else if (p1.age < p2.age) {
        return -1;
      } else {
        return 1;
      }
    }
  }

}
