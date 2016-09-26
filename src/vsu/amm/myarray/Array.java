package vsu.amm.myarray;

import java.lang.reflect.Method;


public class Array<E extends Comparable<E>> {

    private int size;      //реальный размер массива
    private int current_size=0;     //размер массива доступный пользователю
    private E[] elementData;        //массив элементов

    /*конструктор класса с заданным размером*/
    public Array(Class<E> clazz, int size) {
        this.size=size;
        this.elementData=(E[]) java.lang.reflect.Array.newInstance(clazz,size);
    }

    /*конструктор по умолчанию с размером size=5*/
    public Array(Class<E> clazz) {
        this.size=5;
        this.elementData= (E[]) java.lang.reflect.Array.newInstance(clazz,size);
    }

    /*функция увеличения размера массива при выходе за реальные размеры массива*/
    private void increaseSize() {
        if(this.current_size>this.size) {
            this.size=this.size*2;
            E[] elementData_temp_size=this.elementData.clone();
            this.elementData=(E[]) java.lang.reflect.Array.newInstance(this.elementData[0].getClass(), size);
            System.arraycopy(elementData_temp_size,0,this.elementData,0,elementData_temp_size.length);
        }
    }

    /*функция добавления нового элемента в конец массива*/
    public void add(E element) {
        this.current_size++;
        increaseSize();
        this.elementData[this.current_size-1]=element;
    }

    /*функция возвращающая текущее количество элементов в массиве*/
    public int arrayLenght() {
        return this.current_size;
    }

    /*функция возвращающая реальный размер массива*/
    public int arraySize() {
        return this.size;
    }

    /*Вставка элемета в выбранную позицию, с сдвигом на единицу вправо последующих элементов.*/
    public void set(int i,E element) {
        this.current_size++;
        increaseSize();
        for (int j=this.current_size-1;j>i;j--) {
            this.elementData[j]=this.elementData[j-1];
        }
        this.elementData[i]=element;
    }

    /*функция меняющая местами два элемента массива, нужна для реализации сортировки в главной функции*/
    public void swap(int i,int j) {
        E temp=this.elementData[i];
        this.elementData[i]=this.elementData[j];
        this.elementData[j]=temp;
    }

    /*функция возвращающая элемент под выбранным номером*/
    public E get(int i) {
        return this.elementData[i];
    }

    /*функция, которая принимает в качестве параметра метод сортировки (списана с интернета)*/
    public void sort( Method m, Array params ) {
        try {
          Class c = m.getDeclaringClass();
          Object t = c.newInstance();
          m.setAccessible(true);
          m.invoke(t, params);
      }
        catch (Exception x) {
          System.out.print( x.getMessage() );
      }
    }

    /*функция удаления элемента из массива */
    public void remove(int i) {
        this.current_size--;
        E[] elementData_temp=this.elementData.clone();
        this.elementData = (E[]) java.lang.reflect.Array.newInstance(this.elementData[0].getClass(), size);
        for(int j=0;j<this.current_size;j++) {
            if(j<i) {
            this.elementData[j]=elementData_temp[j];
            }
            else if(j>=i) {
                this.elementData[j]=elementData_temp[j+1];
            }
        }
    }

    /* переопределенный метод вывода массива в строку*/
    @Override
    public String toString() {
        String print="";
        for (int i=0;i<this.current_size;i++) {
            print+=this.elementData[i]+" ";
        }
        return print;
    }
}