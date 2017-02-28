###简介
仿美寸滑动效果

![DoubleScrollView](a.gif)
### 测试apk
 [demo.apk](demo.apk)

### 用法
1、添加LinearSnapHelper
	``` linearSnapHelper = new LinearSnapHelper();
    	 linearSnapHelper.attachToRecyclerView(recyclerView);```

2、添加滑动监听器
    ``` ysnowsScrollListener = new YsnowsScrollListener(recyclerView);
        recyclerView.addOnScrollListener(ysnowsScrollListener);```
详细用法见demo


### [IOS效果项目](https://github.com/ltebean/LTInfiniteScrollView)


### License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.```

