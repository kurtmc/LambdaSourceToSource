# LambdaSourceToSource

This is a source to source compiler that takes java 8 -esque lambda expressions and conversts them into valid java 7 anonymous class instantiations.

And example would be taking a piece of code like this:
```Java
Button myButton = (Button) findViewById(R.id.my_button);
myButton.setOnClickListener((View v) -> {
        doAction(v);
});
```

and the output would be this
```Java
Button myButton = (Button) findViewById(R.id.my_button);
myButton.setOnClickListener(
new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                doAction(v);
        }
});
```
