var_name = "outer";

def func() {
    let var_name = "inner";
    print("call func: "..var_name);
}

print("globe before call: "..var_name);

func();

print("globe after call: "..var_name);

