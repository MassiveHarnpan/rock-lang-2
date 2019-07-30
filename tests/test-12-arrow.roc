def makeRepeater(count) {
    (s) => {
        r = "";
        for (let i = 0; i < count; i = i + 1) {
            r = r .. s;
        }
        r;
    };
}

let repeater = makeRepeater(3);
print(repeater("Hello World!!!  "));