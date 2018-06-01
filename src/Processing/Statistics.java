package Processing;

import java.util.*;

/**
 * Created by Owen Daynes on 29/05/2018.
 */
public class Statistics {

    public static class Average {
        // arithmetic mean of a collection

        public static double arithmeticMean(Collection<Double> xs) {
            if (xs.isEmpty()) return 0;
            return xs.stream().reduce(0.0, (x, y) -> x + y) / xs.size();
        }

        // arithmetic mean of varargs or variable length array

        public static double arithmeticMean(double... xs) {
            double result = 0;
            for (double x : xs) {
                result += x;
            }
            return result / xs.length;
        }

        // median element of a collection

        public static double median(Collection<Double> xs) {
            if (xs.isEmpty()) return 0;
            List<Double> ls = new ArrayList<>(xs);
            if (xs.size() == 1) return ls.get(0);
            else {
                int size = ls.size();
                ls.sort((x, y) -> x.compareTo(y));
                int lower = size / 2 - 1;
                int upper = size / 2;
                if (size % 2 == 0) {
                    return (ls.get(lower) + ls.get(upper)) / 2.0;
                } else {
                    return ls.get(upper);
                }
            }
        }

        // median element of varargs or variable length array

        public static double median(double... xs) {
            if (xs.length == 0) return 0;
            if (xs.length == 1) return xs[0];
            else {
                int size = xs.length;
                Arrays.sort(xs);
                int lower = size / 2 - 1;
                int upper = size / 2;
                if (size % 2 == 0) {
                    return (xs[lower] + xs[upper]) / 2.0;
                } else {
                    return xs[upper];
                }
            }
        }

        // mode element of a collection

        public static double mode(Collection<Double> xs) {
            if (xs.size() == 0) return 0;
            if (xs.size() == 1) return xs.iterator().next();

            Map<Double, Integer> unsorted = new HashMap<>();

            for (Double x : xs) {
                unsorted.put(x, unsorted.get(x) != null ? unsorted.get(x) + 1 : 1);
            }

            List<Map.Entry<Double, Integer>> sorted = new ArrayList<>(unsorted.entrySet());

            sorted.sort((first, second) -> second.getValue().compareTo(first.getValue()));

            System.out.println(sorted);

            return sorted.get(0).getKey();
        }


        // mode element of varargs or variable length array

        public static double mode(double... xs) {
            if (xs.length == 0) return 0;
            if (xs.length == 1) return xs[0];

            Map<Double, Integer> unsorted = new HashMap<>();

            for (Double x : xs) {
                unsorted.put(x, unsorted.get(x) != null ? unsorted.get(x) + 1 : 1);
            }

            List<Map.Entry<Double, Integer>> sorted = new ArrayList<>(unsorted.entrySet());

            sorted.sort((first, second) -> second.getValue().compareTo(first.getValue()));

            System.out.println(sorted);

            return sorted.get(0).getKey();
        }

        // range between largest and smallest element in collection

        public static double range(Collection<Double> xs) {
            if (xs.size() == 0 || xs.size() == 1) return 0;
            else {
                List<Double> ls = new ArrayList<>(xs);
                ls.sort((x, y) -> x.compareTo(y));
                double smallest = ls.get(0);
                double largest = ls.get(ls.size() - 1 );
                return largest - smallest;
            }
        }

        // range between largest and smallest element in array

        public static double range(double ... xs) {
            if (xs.length == 0 || xs.length == 1) return 0;
            else {
                Arrays.sort(xs);
                double smallest = xs[0];
                double largest = xs[xs.length - 1];
                return largest - smallest;
            }
        }

        // interquartile range of a collection of values

        public static double interquartileRange(Collection<Double> xs) {
            if (xs.size() == 0 || xs.size() == 1) return 0;
            else {
                List<Double> ls = new ArrayList<>(xs);
                ls.sort((x,y)->x.compareTo(y));
                int markerPos = ls.size() / 2;
                if(ls.size() % 2 == 0) {
                    double q1 = median(ls.subList(0, markerPos));
                    double q3 = median(ls.subList(markerPos + 1, ls.size() - 1));
                    return q3 - q1;
                } else {
                    double q1 = median(ls.subList(0, markerPos - 1));
                    double q3 = median(ls.subList(markerPos + 1, ls.size() - 1));
                    return q3 - q1;
                }
            }
        }


        // interquartile range of an array of values

        public static double interquartileRange(double ... xs) {
            if (xs.length == 0 || xs.length == 1) return 0;
            else {
                Arrays.sort(xs);
                int markerPos = xs.length / 2;
                if(xs.length % 2 == 0) {
                    double q1 = median(Arrays.copyOfRange(xs, 0, markerPos));
                    double q3 = median(Arrays.copyOfRange(xs, markerPos + 1, xs.length + 1));
                    return q3 - q1;
                } else {
                    double q1 = median(Arrays.copyOfRange(xs, 0, markerPos - 1));
                    double q3 = median(Arrays.copyOfRange(xs, markerPos + 1, xs.length + 1));
                    return q3 - q1;
                }
            }
        }

        // variance of a collection of values

        public static double variance(Collection<Double> xs) {
            if(xs.size() < 2) return 0;
            double mean = arithmeticMean(xs);
            double result = 0;
            for(Double x: xs) {
                result += (x - mean) * (x - mean);
            }
            return result / xs.size();
        }

        // variance of an array of values

        public static double variance(double ... xs) {
            if(xs.length < 2) return 0;
            double mean = arithmeticMean(xs);
            double result = 0;
            for(Double x: xs) {
                result += (x - mean) * (x - mean);
            }
            return result / xs.length;
        }

        // standard deviation of a collection of values

        public static double standardDeviation(Collection<Double> xs) {
            return Math.sqrt(variance(xs));
        }

        // standard deviation of an array of values

        public static double standardDeviation(double ... xs) {
            return Math.sqrt(variance(xs));
        }

    }
}
