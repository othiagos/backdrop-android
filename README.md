# backdrop-android

### This library is created based on Backdrop component from material design. [Backdrop](https://material.io/design/components/backdrop.html)

![alt text](https://github.com/clust3rr/backdrop-android/blob/master/images/backdrop2.png)

![alt text](https://github.com/clust3rr/backdrop-android/blob/master/images/backdrop1.png)
1. Back layer
2. Front layer
3. Subtitle (optional)


### Usage

1. Include repository
```
allprojects {
		repositories {
			  ...
			  maven { url 'https://jitpack.io' }
		}
}
```
2. Include project as local library
```
dependencies {
    implementation 'com.github.clust3rr:backdrop-android:v1.0.0'
}
```
3. In onCreate method 
```
Toolbar toolbar = findViewById(R.id.toolbar);
View backLayer = findViewById(R.id.back_layer);
View frontLayer = findViewById(R.id.front_layer);
int duration = 500;
Drawable drawableNavigation = getDrawable(R.drawable.ic_navigation);
Drawable drawableClose = getDrawable(R.drawable.ic_close);
 
backdrop = new Backdrop(context, backLayer, frontLayer, duration);
backdrop.setSupportToolbar(toolbar, drawableMenu, drawableClose);
backdrop.onClickNavigation(v -> {your click listener implementation});
backdrop.setSupportSubtitle(); //optional
backdrop.build();
```
### Public Methods
```
backdrop.isDropped();
backdrop.show();
backdrop.showByViewSize();
backdrop.close();
```
### Compatabiliy

Api 24+

# License

```
MIT License

Copyright (c) 2020 clust3rr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
