import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
//import static org.mockito.

class HorseTest {

    @Test
    public void constructor_ParamName_Null_IllegalArgumentException_Throws(){
        double speed = 1;
        double distance = 2;
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
        assertEquals("Name cannot be null.", thrown.getMessage());
    }

//    @Test
//    public void constructor_ParamName_Null_IllegalArgumentException_Equals(){
//        try
//        Horse h = new Horse(null, 1, 2).;
//        assertEquals("Name cannot be null.", .getMessage());
//    }

    @ParameterizedTest
    @MethodSource("addParamsName")
//    @ValueSource(strings = {" ","  ", "\n","\n\n","\t","\t\t","\t \t")
    public void constructor_ParamName_EmptyAndSpecSymbol_IllegalArgumentException_Throws_Parametrized(String name/*, double speed, double distance*/){
        double speed = 1;
        double distance = 2;
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Name cannot be blank.", thrown.getMessage());
//        String expectedMessage = "Name cannot be blank.";
//        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 2/*speed, distance*/));
//        assertEquals(expectedMessage, thrown.getMessage());
    }

    public static Stream<Arguments> addParamsName() {
        return Stream.of(
                Arguments.of(" " /* 1, 2*/),
                Arguments.of("  "/*, 1, 2*/),
                Arguments.of("\t"/*, 1, 2*/),
                //Arguments.of("\b", 1, 2),
                Arguments.of("\n"/*, 1, 2*/),
                Arguments.of("\r"/*, 1, 2*/),
                Arguments.of("\f"/*, 1, 2*/)//,
                //Arguments.of("\'", 1, 2),
                //Arguments.of("\"", 1, 2),
                //Arguments.of("\\", 1, 2)
        );
    }

    @Test
    public void constructor_ParamSpeed_NegativeNum_IllegalArgumentException_Throws(){
        String name = "nameHorse";
        double speed = -1;
        double distance = 2;
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Speed cannot be negative.", thrown.getMessage());
    }

    @Test
    public void constructor_ParamDistance_NegativeNum_IllegalArgumentException_Throws(){
        String name = "nameHorse";
        double speed = 1;
        double distance = -2;
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Distance cannot be negative.", thrown.getMessage());
    }

    @Test
    void getName_receiveFirstParam_name() {
        String name = "nameHorse";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(name,speed,distance);
        String horseName = horse.getName();
        assertEquals(name,horseName);
    }

    @Test
    void getSpeed_receiveSecondParam_speed() {
        String name = "nameHorse";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(name,speed,distance);
        double horseSpeed = horse.getSpeed();
        assertEquals(speed, horseSpeed);
    }

    @Test
    void getDistance_receiveThirdParam_distance() {
        String name = "nameHorse";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(name,speed,distance);
        double horseDistance = horse.getDistance();
        assertEquals(distance, horseDistance);
    }

    @Test
    void getDistance_receiveWithoutThirdParam_distance_zero() {
        String name = "nameHorse";
        double speed = 1;
        //double distance = 2;
        Horse horse = new Horse(name,speed);
        double horseDistance = horse.getDistance();
        assertEquals(0, horseDistance);
    }

    @Test
    void move_methodCall_getRandomDouble() {
        try (MockedStatic<Horse> horseMock = mockStatic(Horse.class)){
            Horse horse = new Horse("nameHorse",1,2);
            horse.move();
            horseMock.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }

    //from example
    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.5, 0.8, 15, 0, 153})
    void move_distanceEqualFormula_getRandomDouble(double fakeRandomValue) {
        double min = 0.2;
        double max = 0.9;
        String name = "testName";
        double speed = 2.5;
        double distance = 250;
        Horse horse = new Horse(name, speed, distance);
        double expectedDistance = distance + speed * fakeRandomValue;
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);
            horse.move();
        }
        assertEquals(expectedDistance, horse.getDistance());
    }
}