package com.codechen.interview;

/**
 * @author：Java陈序员
 * @date：2025-2-8 9:36
 * @description：浅拷贝与深拷贝 1、对象拷贝(Object Copy)就是将一个对象的属性拷贝到另一个有着相同类类型的对象中去。
 * 在程序中拷贝对象是很常见的，主要是为了在新的上下文环境中复用对象的部分或全部数据。
 * <p>
 * 2、浅拷贝：浅拷贝只复制指向某个对象的指针，而不复制对象本身，新旧对象还是共享同一块内存，类似一个分支
 * 拷贝基本类型：拷贝的就是基本类型的值
 * 拷贝引用类型：拷贝的就是内存地址，因此如果其中一个对象改变了这个地址，就会影响到另一个对象
 * <p>
 * 3、深拷贝：深拷贝会创造一个一摸一样的对象，新对象和源对象不共享内存，修改新对象不会影响源对象。
 * 深拷贝拷贝了源对象的所有制，即使源对象的值发生变化时，拷贝对象的值也不会改变
 * <p>
 * 4、Cloneable 接口：如果没有实现 Cloneable 接口，而调用 clone() 方法会抛出异常 java.lang.CloneNotSupportedException
 */
public class CloneDemo implements Cloneable {

    public static void main(String[] args) throws CloneNotSupportedException {

        CloneDemo cloneDemo = new CloneDemo();
        Object clone = cloneDemo.clone();

        shallowClone();

    }

    private static void shallowClone() throws CloneNotSupportedException {

        Employee employee = new Employee("1101", "张三", "雷军", "CEO");
        // 原始对象:张三==>领导头衔:CEO
        System.out.println("原始对象:" + employee.getEmployeeName() + "==>领导头衔:" + employee.getBoss().getTitle());

        Employee cloneEmployee = (Employee) employee.clone();
        // 拷贝对象:张三==>领导头衔:CEO
        System.out.println("拷贝对象:" + cloneEmployee.getEmployeeName() + "==>领导头衔:" + cloneEmployee.getBoss().getTitle());

        System.out.println("===============拷贝对象修改属性==================");
        cloneEmployee.getBoss().setTitle("CTO");
        // 原始对象:张三==>领导头衔:CTO
        System.out.println("原始对象:" + employee.getEmployeeName() + "==>领导头衔:" + employee.getBoss().getTitle());
        // 拷贝对象:张三==>领导头衔:CTO
        System.out.println("拷贝对象:" + cloneEmployee.getEmployeeName() + "==>领导头衔:" + cloneEmployee.getBoss().getTitle());
    }

    static class Boss implements Cloneable {
        private String bossName;
        private String title;

        public Boss() {
        }

        public Boss(String bossName, String title) {
            this.bossName = bossName;
            this.title = title;
        }

        public String getBossName() {
            return bossName;
        }

        public void setBossName(String bossName) {
            this.bossName = bossName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    static class Employee implements Cloneable {

        private String employeeNo;
        private String employeeName;
        private Boss boss;

        public Employee(String employeeNo, String employeeName, String bossName, String bossTitle) {
            this.employeeNo = employeeNo;
            this.employeeName = employeeName;
            this.boss = new Boss(bossName, bossTitle);
        }

        public String getEmployeeNo() {
            return employeeNo;
        }

        public void setEmployeeNo(String employeeNo) {
            this.employeeNo = employeeNo;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public Boss getBoss() {
            return boss;
        }

        public void setBoss(Boss boss) {
            this.boss = boss;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
//            return super.clone();
            // 重写拷贝方法，实现深拷贝
            return new Employee(employeeNo, employeeName, boss.getBossName(), boss.getTitle());
        }
    }
}
