# 什么是 KMP

KMP 是由三位学者发明的：Knuth、Morris和Pratt，所以取了这三位学者名字的首字母。

# KMP 有什么用

KMP 主要用在字符串匹配上。

KMP 的主要思想是 **当出现字符串不匹配时，可以知道一部分之前已经匹配的文本内容，可以利用这些信息避免从头再去做匹配。**

所以如何记录已经匹配的文本内容是 KMP 的重点，也是 next 数组（前缀表）肩负的重任。

# 什么是前缀表

在写 KMP 算法时需要一个辅助的前缀表数组，一般前缀表用 next 名称。

**前缀表是用来回退的，它记录了模式串与主串（文本串）不匹配的时候，模式串应该从哪里开始重新匹配。**

举一个例子：

要在文本串：aabaabaafa 中查找是否出现过一个模式串 aabaaf。

下标值：0 1 2 3 4 5 6 7 8 9
文本串：a a b a a b a a f a
模式串：a a b a a f

当文本串指针移动到下标 5 即 aabaab、模式串指针移动到 aabaaf 时，此时字符串不匹配，因为有前缀表，模式串会退回到下标 2 即 aab，然后重新移动直到在文本串找到 aabaaf。

首先要知道 **前缀表的任务是当前位置匹配失败，找到之前已经匹配上的位置，再重新匹配**，此时也意味着在某个字符失配时，前缀表会告诉你下一步匹配中，模式串应该跳到哪个位置。

先分析前缀表是怎么回退的。

下标值：0 1 2 3 4 5
模式串：a a b a a f

当移动到下标 5 时遇到不匹配，模式串指向 f，然后就找到了下标 2 指向 b，继续匹配。

下标 5 之前这部分的字符串（aabaa）的 **最长相等的前缀** 和 **最长相等后缀** 字符串 是子字符串 aa，因为找到了最长相等的前缀和后缀，匹配失败的位置是 后缀子字符串（第二次aa）的后面，那么我们找到与其相同的 **前缀的后面** 重新匹配就可以了。

# 如何计算前缀表

下标值：0 1 2 3 4 5
模式串：a a b a a f

- 长度为前1个字符的子串 a，最长相同前后缀的长度为0

- 长度为前2个字符的子串 aa，最长相同前后缀的长度为1

- 长度为前3个字符的子串 aab，最长相同前后缀的长度为0

- 长度为前4个字符的子串 aaba，最长相同前后缀的长度为1

- 长度为前5个字符的子串 aabaa，最长相同前后缀的长度为2

- 长度为前6个字符的子串 aabaaf，最长相同前后缀的长度为0

根据上面步骤求得前缀表元素：

下标值：0 1 2 3 4 5
模式串：a a b a a f
前缀表：0 1 0 1 2 0

可以看出模式串与前缀表对应位置的数字表示的是：**下标 i 之前（包括i）的字符串中，有多大长度的相同前缀后缀**。

根据上面的前缀表，当字符串不匹配时指针应该移动的位置：

下标值：0 1 2 3 4 5 6 7 8 9
文本串：a a b a a b a a f a
模式串：a a b a a f
前缀表：0 1 0 1 2 0

当下标为5时找到的不匹配位置时，此时要看模式串的前一个字符的前缀表数值是多少。

为什么要前一个字符的前缀表的数值呢？因为要找前面字符串的最长相同的前缀和后缀。

前一个字符的前缀表数值是 2，所以把下标移动到下标 2 的位置继续匹配。

# 构造前缀表

前缀表习惯使用 next 数组命名。构造 next 数组其实就是计算模式串，主要有三步：

- 初始化

- 处理前后缀不相同的情况

- 处理前后缀相同的情况

## 初始化

定义两个指针 i 和 j，j 指向前缀起始位置，i 指向后缀起始位置。对 next 数组进行初始化赋值：

```java
int j = -1;
next[0] = j;
```

j 初始化为 -1，其实是前缀表要统一减一的操作，并不是必须的也可以是初始化为 0，但是操作会有所不同。

next[i] 表示 i（包括 i）之前最长相同的前后缀长度（其实就是 j），所以初始化 next[0] = j。

下标值：  -1  0  1  2  3  4  5
模式串：      a  a  b  a  a  f
next[i]： j -1

## 处理前后缀不相同的情况

因为 j 初始化为-1，那么 i 就从 1 开始，进行 s[i] 与 s[j+1] 的比较。如下：

下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    0
         j s[j+1] s[i]

```java
for (int i = 1; i < s.length(); i++) 
```

下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    0
                s[j+1] s[i]

当 s[i] 与 s[j+1] 不相同，也就是遇到前后缀末尾不相同的情况，就要向前回退。怎么回退呢？

next[j] 记录着 j（包括 j）之前的子串的相同前后缀的长度。

当 s[i] 与 s[j+1] 不相同，就要找 j+1 前一个元素在 next 数组里的值（就是 next[j]）。

如上 s[i] = b, s[j+1] = a 不相同，找 j+1 前一个元素 next[j] = -1。

所以处理前后缀不相同的情况代码如下：

```java
while (j >= 0 && s[i] != s[j+1]) { // 前后缀不相同了
    j = next[j]; // 向前回退
}
```

下标值：  -1    0     1     2    3    4    5
模式串：        a     a     b    a    a    f
next[i]：     -1     0    -1
             s[j] s[j+1] s[i]

## 处理前后缀相同的情况

当 s[i] 与 s[j+1] 相同，同时向后移动 i 和 j 说明找到了相同的前后缀，

同时还要将 j（前缀的长度）赋给 next[i]，因为 next[i] 要记录相同前后缀的长度。

```java
if (s[i] == s[j+1]) { // 找到相同前后缀
    j++;    
}
next[i] = j;
```

最后整体构建 next 数组代码如下：

```java
int[] getNext(String s) {
    int[] next = new int[s.length()];
    int j = -1;
    next[0] = j;
    for (int i = 1; i < s.length(); i++) { // 注意 i 从 1 开始
        while (j >= 0 && s[i] != j[j+1]) { // 前后缀不相同
            j = next[j]; // 向前回退到上一个前缀长度为 -1 的位置，即回退到上一个不相同前缀的位置
        }    
        if (s[i] == s[j+1]) { // 前后缀相同
            j++;
        }
        next[i] = j; // 将 j（前缀的长度）赋给 next[i]
    }
    return next;
}
```

再留意下前缀表和相关参数的定义：

- next 数组是前缀表

- j 是最长相同前后缀的长度

构造前缀表流程如下：

1、
下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    
         j  s[j+1]

j = -1, i = 0
next[0] = j = -1

2、
下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    0    
         j s[j+1] s[i]

i = 1, j = -1, s[i] = a, s[j+1] = a
s[i] == s[j+1], j = j++ = 0
next[i] = next[1] = j = 0

3、
下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    0    
              j s[j+1] s[i]

i = 2, j = 0, s[i] = b, s[j+1] = a
s[i] != s[j+1]
loop:
j = next[j] = next[0] = -1
j < 0，跳出循环
s[i] = s[2] = b, s[j+1] = a
s[i] != s[j+1]
next[i] = next[2] = j = -1

4、
下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    0   -1
         j s[j+1]           s[i]

i = 3, j = -1, s[i] = a, s[j+1] = a
s[i] == s[j+1], j = j++ = 0
next[i] = next[3] = j = 0

5、
下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    0   -1    0
              j s[j+1]           s[i]

i = 4, j = 0, s[i] = a, s[j+1] = a
s[i] == s[j+1], j = j++ = 1
next[i] = next[4] = j = 1

6、
下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    0   -1    0    1
                    j  s[j+1]         s[i]

i = 5, j = 1, s[i] = f, s[j+1] = b
s[i] != s[j+1]
loop: 
j = next[j] = next[1] = 0
j = next[j] = next[0] = -1
j < 0, 跳出循环
s[i] = f, s[j+1] = a
s[i] != s[j+1]
next[i] = next[5] = j = -1

下标值：  -1    0    1    2    3    4    5
模式串：        a    a    b    a    a    f
next[i]：     -1    0   -1    0    1   -1

# 使用前缀表 next 数组做匹配

在文本串 s 里查找是否出现过模式串 t

定义两个下标 j 指向模式串起始位置，i 指向文本串起始位置

j 初始值依然为 -1，因为 next 数组里记录的起始位置为 -1

i 从0开始，遍历文本串：

```java
for (int i = 0; i < s.length(); i++)
```

接下来是 s[i] 与 t[j+1]（因为 j 从 -1 开始）进行比较

如果 s[i] 与 t[j+1] 不相同，j 就要从 next 数组里寻找下一个匹配的位置：

```java
while (j >= 0 && s[i] != t[j+1]) {
    j = next[j];    
}
```

如果 s[i] 与 t[j+1] 相同，那么 i 和 j 同时向后移动：

```java
if (s[i] == t[j+1]) {
    j++;    
}
```

如何判断在文本串 s 里出现了模式串 t 呢？

如果 j 指向了模式串 t 的末尾，说明模式串 t 完全匹配文本串 s 里的某个子串

如果题目要求文本串中找出模式串出现的第一个位置（从 0 开始），所以返回当前文本串匹配模式串的位置 i - 模式串的长度，就是文本串中出现模式串的第一个位置

```java
if (j == t.length() - 1) {
    return i - t.length() + 1;    
}
```

使用 next 数组用模式串匹配文本串整体代码如下：

```java
int j = -1; // 因为 next 数组里记录的起始位置为-1
for (int i = 0; i < s.length(); i++) { // 注意 i 从0开始
    while (j >= 0 && s[i] != t[j+1]) { // 不匹配
        j = next[j]; // j 寻找之前匹配的位置
    }    
    if (s[i] == t[j+1]) { // 匹配，j 和 i 同时向后移动
        j++; // i 的增加在 for 循环里
    }
    if (j == t.length() - 1) { // 文本串s 里出现了模式串 t
        return i - t.length() + 1;    
    }
}
```

# 前缀表不减1的处理

```java
int[] getNext(String s) {
    int[] next = new int[s.length()];
    int j = 0;
    next[0] = j;
    for (int i = 1; i < s.length(); i++) { // 注意 i 从1开始
        while (j >= 0 && s[i] != j[j]) { // 前后缀不相同
            j = next[j - 1]; // 向前回退
        }    
        if (s[i] == s[j]) { // 前后缀相同
            j++;
        }
        next[i] = j; // 将 j（前缀的长度）赋给 next[i]
    }
    return next;
}
```
