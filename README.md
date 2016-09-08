[![CircleCI](https://circleci.com/gh/rockerhieu/emojiconize/tree/master.svg?style=svg)](https://circleci.com/gh/rockerhieu/emojiconize/tree/master)
![emojicon on Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.rockerhieu/emojiconize/badge.svg)

## Emojiconize

Render emoji (using [emojicon](https://github.com/rockerhieu/emojicon)) in your Android application with just 1 line of code.

## Example

```java
import io.github.rockerhieu.emojiconize.Emojiconize;

// Your activity must be a subclass of AppCompatActivity
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// Emojiconize the whole activity, must call before `super.onCreate()`
        Emojiconize.activity(this).go();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

![image](https://github.com/rockerhieu/emojiconize/raw/master/assets/sample.jpg)

## Usage

* `Emojiconize.activity(activity).go()`: support emoji rendering for the given `activity`, must call before `activity.super.onCreate()`
* `Emojiconize.view(view).go()`: support emoji rendering for the given view and all of its children.

## Building in IntelliJ

Via Gradle:

```
compile 'io.github.rockerhieu:emojiconize:<latest-version>'
```

## Building in Eclipse

![Go home you're drunk](http://img2.wikia.nocookie.net/__cb20130819142928/cardfight/images/thumb/5/55/Go-home-youre-drunk.jpg/500px-Go-home-youre-drunk.jpg)

## Acknowledgements

Emojiconize uses [emojicon](https://github.com/rockerhieu/emojicon) for rendering emoji and is based on the idea of `MdLayoutInflaterFactory` from [android-md-core](https://github.com/henrytao-me/android-md-core/).

## Contributing

Please fork this repository and contribute back using
[pull requests](https://github.com/rockerhieu/emojiconize/pulls).

Any contributions, large or small, major features, bug fixes, additional
language translations, unit/integration tests are welcomed and appreciated
but will be thoroughly reviewed and discussed.

## License

* [The MIT License](https://opensource.org/licenses/MIT)

```
Copyright (c) 2016 Hieu Rocker

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.
```
