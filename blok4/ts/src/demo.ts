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

interface HasNonsense {
    nonsense: boolean
}

let iets = {firstname: "Bram", age: 46, nonsense: true}
iets.age
iets.firstname

let n = {firstname: "Bram", age: 46, nonsense: true} as HasNonsense
n.nonsense

let [k, v] = [...keyValue]
console.log(k);
console.log(v);
//
// function returnThis() {
//     return this;
// }

// console.log(returnThis());

class Client {

    name: string

    constructor(n: string = "", age: number = 0) {
        this.name = n
    }
}

let c1 = new Client("")
let c2 = new Client("", 42)
