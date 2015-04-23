package arrayhelper.builder;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ivan on 23.04.15.
 */

public class ArrayInnerUnionMockitoTest {


    private static final String[] NUMBER_NAME = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
    private static final int NUMBER_NAME_LEN = NUMBER_NAME.length;
    private static final String OTHER_NUMBERS_NAME = "Some";
    private static final int OTHER_NUMBERS_NAME_THRESHOLD = 100;
    private static final int EMPTY_NUMBERS_NAME_THRESHOLD = 200;


    private String GetNumberName(int number){
        if (number <= NUMBER_NAME_LEN)
            return NUMBER_NAME[number-1];

        if (number <= OTHER_NUMBERS_NAME_THRESHOLD)
            return OTHER_NUMBERS_NAME;

        if (number <= EMPTY_NUMBERS_NAME_THRESHOLD)
            return "";

        return null;

    }


    protected PojoNumber[] GetTestArray(int[] array){

        if (null != array) {
            int i = 0;
            PojoNumber[] resultArray = new PojoNumber[array.length];
            for (int item : array) {
                resultArray[i] = new PojoNumber.PojoNumberBuilder(item)
                        .name(GetNumberName(item))
                        .build();
                i++;
            }
            return resultArray;
        }
        else
            return null;

    }


    @Test
    public void testMockitoArraysInnerUnion() throws Exception {

        //init input values
        HashSet<PojoNumber> lArray = new HashSet<PojoNumber>(Arrays.asList(GetTestArray(new int[]{1, 5, 4, 23, 65, 32, 78, 1})));
        HashSet<PojoNumber> rArray = new HashSet<PojoNumber>(Arrays.asList(GetTestArray(new int[]{3, 5, 24, 4, 1, 2, 34, 45, 32, 5, 32})));

        //init expected value
        HashSet<PojoNumber> expectedValue = new HashSet<PojoNumber>(Arrays.asList(GetTestArray(new int[]{5,4,32,1})));

//init mocks
        ArrayHelper arrayHelper = mock(ArrayHelper.class);
        when(arrayHelper.arraysInnerUnion(lArray,rArray))
                .thenReturn(expectedValue);

//init test class
        ArrayHelperDelegation testClass = new ArrayHelperDelegation(arrayHelper);


        HashSet<PojoNumber> returnedValue = testClass.arraysInnerUnion(lArray,rArray);

        //assertation

        assertEquals(expectedValue, returnedValue);

    }





}
