package com.building.gr151l5st7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//вызов модулей Джаваскада
import eu.printingin3d.javascad.coords.Angles3d;
import eu.printingin3d.javascad.models.Abstract3dModel;
import eu.printingin3d.javascad.coords.Coords3d;
import eu.printingin3d.javascad.coords.Dims3d;
import eu.printingin3d.javascad.models.*;
import eu.printingin3d.javascad.tranzitions.Difference;
import eu.printingin3d.javascad.tranzitions.Union;

public class Building extends Union {
    	private static final int coord = -50;
	
	public Building() {
		super(getModels());
	}



	private static List<Abstract3dModel> getModels() {
		List<Abstract3dModel> models = new ArrayList();
                
                //Фундамент
                Difference Low_Part = new Difference(
                   new Cube(new Dims3d(100, 100, 3)).move(new Coords3d(0, 0, -2)),
                   new Cube(new Dims3d(95, 95, 2))
                );
		
                //Окна среднего блока
                Union Windows_low = new Union(
                   new Cube(new Dims3d(8, 5, 8)).move(new Coords3d(8, 35, 5)),
                   new Cube(new Dims3d(8, 5, 8)).move(new Coords3d(-8, 35, 5)),
                   new Cube(new Dims3d(8, 5, 8)).move(new Coords3d(24, 35, 5)),
                   new Cube(new Dims3d(8, 5, 8)).move(new Coords3d(-24, 35, 5))
                );
                
                //Средний блок
		Difference Medium_Part = new Difference(
                   new Cube(new Dims3d(70, 70, 20)).move(new Coords3d(0, 0, 8)),
                   new Cube(new Dims3d(63, 63, 5)).move(new Coords3d(0, 0, 20)),
                   Windows_low,
                   Windows_low.rotate(new Angles3d(0,0,90)),
                   Windows_low.rotate(new Angles3d(0,0,-90)),
                   Windows_low.rotate(new Angles3d(0,0,180))   
		);
                
                //Окна на основном блоке
                List<Abstract3dModel> windows = new ArrayList<Abstract3dModel>();
                for (int i = coord; i <= 13; i += 13) {
                   windows.add(new Cube(new Dims3d(15, 7, 8)).move(new Coords3d(28, -13.5, i)));
                   windows.add(new Cube(new Dims3d(15, 8, 8)).move(new Coords3d(28, 0, i)));
                   windows.add(new Cube(new Dims3d(15, 7, 8)).move(new Coords3d(28, 13.5, i)));
                   
                } 
                
                Union windows_all = new Union(
                       windows
                );
                
                //Облицовка
                Union Surface = new Union(
                   new Cube(new Dims3d(6, 2, 105)).move(new Coords3d(7, 23, 52)),
                   new Cube(new Dims3d(6, 2, 105)).move(new Coords3d(-7, 23, 52)),
                   new Cube(new Dims3d(6, 2, 105)).move(new Coords3d(20, 23, 52)),
                   new Cube(new Dims3d(6, 2, 105)).move(new Coords3d(-20, 23, 52))
                );
          
             
                //Основной блок
                Difference High_Part = new Difference(
                    new Cube(new Dims3d(45, 45, 100)).move(new Coords3d(0, 0, 50)),
                    new Cube(new Dims3d(40, 40, 5)).move(new Coords3d(0, 0, 100)),
                    windows_all.move(new Coords3d(0, 0, 85)),
                    windows_all.move(new Coords3d(0, 0, 85)).rotate(new Angles3d(0,0,90)),    
                    windows_all.move(new Coords3d(0, 0, 85)).rotate(new Angles3d(0,0,-90)), 
                    windows_all.move(new Coords3d(0, 0, 85)).rotate(new Angles3d(0,0,180)) 
                );
                //Крыша
                Difference Floor_part = new Difference(
                  new Prism(15, 15, 4,4).move(new Coords3d(0, 0, 132)).rotate(new Angles3d(0,0,45)), 
                  new Cylinder(30,0.5).move(new Coords3d(0, 0, 147)),
                  new Sphere(1.5).move(new Coords3d(0, 0, 162))
                );
                
                //Башня + окна
                Difference floor_window = new Difference( 
                  new Cube(new Dims3d(20, 20, 50)).move(new Coords3d(0, 0, 100)),
                  new Cube(new Dims3d(10, 6, 8)).move(new Coords3d(0, 10, 115)),
                  new Cube(new Dims3d(10, 6, 8)).move(new Coords3d(0, 10, 115)).rotate(new Angles3d(0,0,90)),
                        new Cube(new Dims3d(10, 6, 8)).move(new Coords3d(0, 10, 115)).rotate(new Angles3d(0,0,-90)),
                        new Cube(new Dims3d(10, 6, 8)).move(new Coords3d(0, 10, 115)).rotate(new Angles3d(0,0,180))
                );
                
                //Diferece() - для удаления. Первый аргумент - блок, от которого удаляют.
                //Union() - объединение. Тут все проще. 
                //Вызов построения моделей
                models.add(Low_Part);
                models.add(Medium_Part);
                models.add(High_Part);
                models.add(Surface);
                models.add(Surface.rotate(new Angles3d(0,0,90)));
                models.add(Surface.rotate(new Angles3d(0,0,-90)));
                models.add(Surface.rotate(new Angles3d(0,0,180)));
                models.add(Floor_part);
                models.add(floor_window);
		return models;
                
	}

}