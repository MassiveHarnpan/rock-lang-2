def millis_2_second(millis) {
    millis / 1000;
}

def second() {
    time() / 1000;
}


start = second();
print("Start at "..start);

sum = 0;
for (i = 0; i < 1000; i = i + 1) {
    sum = sum + i;
}

end = second();
print("End at "..end);

print("Time cast is "..(end - start).." s");
print("Sum(1000) = "..sum);