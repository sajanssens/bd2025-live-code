let i = ""
console.log((typeof i));
let keyValue: [number, string] = [1, "Bram"]
let key = keyValue[0];
let value = keyValue[1];

// console.log(`Hello ${i}`)

interface Person {
    firstname: string,
    age: number
}

let person = {firstname: "Bram", age: 46, nonsense: true} as Person

let [k, v] = [...keyValue]
console.log(k);
console.log(v);
//
// function returnThis() {
//     return this;
// }

// console.log(returnThis());

class Client {

    constructor(n: string, age: number = 0) {

    }
}

let c1 = new Client("")
let c2 = new Client("", 42)
