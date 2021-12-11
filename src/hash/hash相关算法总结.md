# 数组作为哈希表的关键字

在算法题中如果有提醒：**字符串中仅包含小写字母**，就可以尝试使用数组作为哈希表存储字符。

```java
int[] map = new int[26];
for (char c : str.toCharArray()) {
    map[c - 'a']++; // 存储字符出现的次数，索引为0-26
}
```

- 字符转索引：`int index = c - 'a'`，对应数组索引 0 - 26

- 索引转字符：`char c = (char) (i + 'a')`，对应ASCII表的小写字母字符 a - z

# 需要对数组排序

当算法题中有提醒 **不能重复** 时，需要再操作数组前就对数组先排序。

```java
Arrays.sort(nums);
for (int i = 0; i < nums.length(); i++) {
    if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重  
    // ... 处理逻辑
}
```