def second() {
    time() / 1000;
}


def fib_1(n) {
    if (n > 2) {
        r = fib_1(n - 1) + fib_1(n - 2);
        #print("fib("..n..") = "..r);
        r;
    } else {
        #print("fib("..n..") = 1");
        1;
    }
}

def fib_2(n) {
    a = 1;
    b = 1;
    for (i = 2; i < n; i = i + 1) {
        c = b;
        b = a + b;
        a = c;
    }
    b;
}

def test(fib_fn, number) {
    start = second();

    result = fib_fn(number);

    end = second();

    print("Time cast is "..(end - start).." s");
    print("Fib(" .. number .. ") = " .. result);
}

num = 32;
print("Test fib_1");
test(fib_1, num);
print("Test fib_2");
test(fib_2, num);
print("Test over");

