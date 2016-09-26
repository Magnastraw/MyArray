package vsu.amm.myarray;

import java.lang.reflect.Method;

public class ТestСlass {

    /*функция сортировки
    * проблема при сортировки большого массива, например 200000+ тысяч значений,
    * */
    public void sort_my(Array a_r)  {
        for(int i=0;i<a_r.arrayLenght();i++) {
            for(int j=0;j<a_r.arrayLenght()-i-1;j++) {
                if(a_r.get(j+1).compareTo(a_r.get(j))>0) {
                   a_r.swap(j+1,j);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        /*для создания объекта необходимо явно указывать класс в параметрах*/
        Array<Double> mas=new Array<Double>(Double.class,300000);
        Method sort_my = ТestСlass.class.getMethod("sort_my", Array.class);
        for(int i=0;i<3000;i++) {
            /*Для передачи аргумента в функцию требуется явно приводить тип аргумента к типу класса*/
            mas.add(10.0+i);
        }
        mas.sort(sort_my,mas);
    }
}
