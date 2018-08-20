public class HelloWorld {

    public static void main(String[] args) {
        int a = 1;
        int b = a;
        int c = b + 1;
        String result;
        if(a > 0 && b > 0 && c >0){
            if(a + b > c)
            {
                if(a == b || b ==c || a ==c)
                {
                    if(a == b && b == c)
                    {
                        result =  "equilateral";
                    }
                    result =  "isosceles";
                }
                else{
                    result =  "scalene";
                }
            }
            else{
                result =  "Not Triangle";
            }
        }
        else{
            result =  "Not Triangle";
        }
    }

}
