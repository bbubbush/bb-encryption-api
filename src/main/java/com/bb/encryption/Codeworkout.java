package com.bb.encryption;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Codeworkout {
  public static void main(String[] args) {
    squareUp(4);
  }

  public static int[] zeroFront(int[] nums) {
    return IntStream.concat(
      Arrays.stream(nums).filter(i -> i == 0)
      , Arrays.stream(nums).filter(i -> i != 0)
    ).toArray();
  }

  public static int[] squareUp(int n) {
    int[] result = new int[n * n];
    int value = 0;
    int maxValue = n + 1;
    for (int i = result.length; i > 0; i--) {
      if (i % n == 0) {
        value = 1;
        maxValue--;
      }
      if (maxValue >= value) {
        result[i - 1] = value++;
      } else {
        result[i - 1] = 0;
      }
    }
    return result;
  }
}
