public class SortingDemo {
    
    class Employee {
        int salary;
        int age;
        Employee(int salary, int age) {
            this.salary = salary;
            this.age = age;
        };
        @Override
        public String toString() {
            return this.salary + " :" + this.age;
        }
    };
    
    private static void main () {
        int[] intArray = {5, 2, 8, 1, 6};

        // Sorting in ascending order
        Arrays.sort(intArray);

        // Display the sorted array
        System.out.println("Sorted in ascending order: " + Arrays.toString(intArray));

        // YOU CANNOT PASS A COMPARATOR FOR PRIMITIVE TYPES AS THEY DO NOT EXTEND OBJECT CLASS. 
        // FOR DESCENDING ORDER, SORT IN ASCENDING AND THEN REVERSE THE ARRAY


        Employee[] employees = {
            new Employee(50000, 30),
            new Employee(60000, 25),
            new Employee(45000, 35),
            new Employee(50000, 28),
            // Add more employees as needed
        };

        // Sort employees by salary and then by age using a lambda expression
        Arrays.sort(employees, 
            (e1, e2) -> {
                int salaryComparison = Integer.compare(e1.salary, e2.salary);
                return (salaryComparison != 0) ? salaryComparison : Integer.compare(e1.age, e2.age);
            }
        );

        // SORTING COLLECTIONS SUCH AS LISTS
        List<Integer> integerList = new ArrayList<>();
        integerList.add(5);
        integerList.add(2);
        integerList.add(8);
        integerList.add(1);
        integerList.add(6);

        // Sorting in ascending order using Collections.sort
        Collections.sort(integerList);

        // To SORT IN REVERSE ORDER USE THIS TRICK IN LAMBDA
        Collections.sort(integerList, (a, b) -> {
            return Integer.compare(b,a);
        });
        // ALTERNATIVELY
        Collections.sort(integerList, Collections.reverseOrder());

        // SORT LIST IN REVERSE ORDER
        List<String> stringList = new ArrayList<>();
        stringList.add("Banana");
        stringList.add("Orange");
        stringList.add("Apple");
        stringList.add("Grapes");

        // Sort strings in reverse order
        Collections.sort(stringList, Collections.reverseOrder());

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(50000, 30));
        employeeList.add(new Employee(60000, 25));
        employeeList.add(new Employee(45000, 35));
        employeeList.add(new Employee(50000, 28));

        Collections.sort(employeeList, (e1, e2) -> {
            int compareSalary = Integer.compare(e1.salary, e2.salary);
            return compareSalary != 0 ? compareSalary : Integer.compare(e1.age, e2.age);
        });
    }

    
}