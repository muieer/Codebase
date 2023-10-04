package main

import "fmt"

func main() {
	fmt.Println(sayHello("Go"))
}

func sayHello(name string) string {
	return "Hello, " + name
}
