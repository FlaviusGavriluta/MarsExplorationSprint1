
# Mars Exploration - Sprint 1

### What are you going to learn?
* Practice open-closed principle
* Use multidimensional arrays
* Work with <span style="color:red">Random</span>
* Practice data-driven testing
* Use file I/O
* Practice single responsibility principle
* Practice interface segregation principle
* Practice dependency injenction

## Story

Looks like humanity will have a bright future after all: the colonization of Mars has finally started. But it is no small effort. To avoid wasting billions of dollars worth of equipment in space, a lot of simulation exercises need to be done – here on Earth.

To fully flesh out the Mars rovers software, sample maps are needed to calibrate its functions. This is where you come into the picture. Your team is selected to be a part of this wonderful adventure. Your first task is to create an application that can generate randomized maps of Mars, based on some input requirements. These requirements can change quickly, so you need to build a robust software that can handle changing requirements in a flexible way.

In this first iteration the following objects are to be placed on the map:

* '#' - mountains: these are always multi-dimensional, and don't have a location preference (placed randomly), dimension growth: 3
* '&' - pits: multi-dimensional objects, random placement, dimension growth: 10
* '%' - minerals: single-dimensional object, placed next to mountains, dimension growth: 0
* '*' - pockets of water: single dimensional objects, placed next to pits, dimension growth: 0

For a technical overview of the application, see this:

## Technical Design
The purpose of Sprint 1 of the Mars Explorer application is to generate a randomized imaginary map of Mars. The map we want to create is a square map: it's like a chessboard but with an arbitrary number of fields.

We have four type of objects or elements we wish to place on the map: mountains, pits, minerals, and water. Some elements are one-dimensional meaning they take up only one square. These are minerals and water. The rest, mountains and pits, can take up any number of squares. Also, some elements could have a preference with regards to their placement on the map. For example water is typically found next to pits.

The starter code contains some sample maps in the <span style="color:red">resourses</span> package. These maps have been generated with the following parameters:

* mountain: 2x20, 1x30
* pit: 2x10, 1x20
* mineral, water: 10x1

### Configuration
The application uses configuration objects to describe each of these elements. For example, the config object for the mountain element is already provided in the starter code:

```
String mountainSymbol = "#";

MapElementConfiguration mountainsCfg = new MapElementConfiguration(
        mountainSymbol,
        "mountain",
        List.of(
                new ElementToSize(2, 20),
                new ElementToSize(1, 30)
        ),
        3, 
        ""
);
```


This declares that the mountains are represented with the symbol '#', are named 'mountain', and we'd like to have three mountain ranges on the map: two with the size of 20 and one with the size of 30.

The last parameter is the dimension growth: this means how much extra dimensions we'd like to have for our mountains to get some empty space within the mountain range.

For example, the minimum squares required for a mountain range with the size of 20 is 25 (5x5). With the dimension growth parameter, however, we can add more dimensions, so the # characters representing a mountain will be more spread out. In this case, we are adding three extra dimensions to each mountain range, so the 25 squares will turn into 64 (8x8).

We place the 20 <span style="color:red">#</span> characters on 64 squares instead of 25, which will result in a more sparse placement (there will be more empty space).


The <span style="color:red">MapElementConfiguration</span> objects are collected into a <span style="color:red">MapConfiguration</span> object, which is then processed by the <span style="color:red">MapElementsGenerator</span> which transforms them into actual <span style="color:red">MapElement</span> objects. A <span style="color:red">MapElement</span> represents the occupied space with a multidimensional array.

In the final step, the <span style="color:red">MapGenerator</span> creates a randomized map and places the previously generated elements on it with the help of the <span style="color:red">MapElementPlacer</span>. The generated <span style="color:red">Map</span> object can be written onto a console or into a file using its overridden <span style="color:red">toString()</span> method.

The application makes good use of the **Open/Closed software development principle**. It is possible to define & generate new types of elements simply by adding a related config object. No changes are required in either the <span style="color:red">MapelementGenerator</span> or in the <span style="color:red">MapGenerator</span> or in any other components to extend the functionality of our application.

Of course we also make use of the **_Single Responsibility Principle** and the **Interface Segregation Principle** in the app.


### Modules
Below you will find a small summary about each module.

1. **Configuration**: contains the model classes related to the configuration of the map, and the configuration validator service.
2. **Calculators**: provide useful functions to work with coordinates and multidimensional arrays.
3. **MapElements**: contains all the components related to creating the map and its elements.
4. **Output**: has services that help with writing the map to different outputs (e.g. a file)

By breaking up the functionality of our app into modules, we can allow a team of multiple members to work on the features in parallel, thus optimizing the development cycle.