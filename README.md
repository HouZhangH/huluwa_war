# 葫芦娃蛇精世纪大战
#### 151220039 侯漳姮
------

这是关于葫芦娃和蛇精的一份README文件，文件中包括以下:
> * 基础功能介绍
> * 按键功能介绍
> * 框架代码介绍
> * 测试方法介绍

## 1.基础功能介绍
![background](http://i1.bvimg.com/626694/7dc6b534932a114a.jpg)


开始界面如图所示，葫芦娃呈长蛇阵，而蛇精率领小喽啰蝎子精呈雁行阵，双方呈对峙局面。


![fighting](http://i1.bvimg.com/626694/12447ead5083d594.jpg)
开始战斗时，葫芦娃各使出他们的本领（图中七娃不是战斗状态）：
|葫芦娃|本领|
| - | - |
|大娃|大力士|
|二娃|火眼金睛|
|三娃|铜皮铁骨|
|四娃|喷水、吸水|
|五娃|操纵火|
|六娃|隐身|
|七娃|宝葫芦|
|老爷爷|松果攻击|
当敌人被打败时，他们将会回到peace状态（如图中的七娃），而敌人也会倒地死亡。

![win_lose](http://i1.bvimg.com/626694/3f2f578195030b0d.jpg)
活着的人会主动寻找敌人攻击，直到敌人全部死亡，活着的一方赢得胜利。

## 2.按键功能介绍
![running](http://i1.bvimg.com/626694/ddf4eac7af62f7d2.jpg)
按下空格键的时候，敌人和葫芦娃会朝着对方的方向运动。直到碰到对方就开始打架。
![save](http://i1.bvimg.com/626694/d4461ba079d52bb5.jpg)
当战斗结束时，按下字母A键，可以选择保存的路径和保存txt文件的文件名。
![read](http://i1.bvimg.com/626694/40e9a7a7a423ce06.jpg)、
战斗一开始时，按下字母L键，也可以读取历史文件进行战斗回放。


## 3.框架代码介绍
![read](http://i2.bvimg.com/626694/9fac3d3a094e4904.jpg)

一些老师框架代码就不详细说明
|类|功能|意义|
|--|--|--|
|Creature|定义生物类型|定义一个接口让huluwa等生物可以继承|
|Field|定义战斗场景|整个世界的初始化还有定义关于战斗的各种方法|
|Ground|定义窗口|窗口大小|
|laoyeye|定义生物老爷爷|有关老爷爷的方法|
|Player|定义生物葫芦娃|有关葫芦娃的方法|
|Reader|读取文件一条信息|用于读取文件回放战斗场景|
|Record_reader|调用Reader来读取文件|调用Reader的对象读取|
|Saver|存储一条信息到文件中|用户记录战斗情况|
|shejing|定义生物蛇精|有关蛇精的方法|
|Thing2D|-|-|
|Tile|-|-|
|xiaolouluo|定义生物小喽啰|有关小喽啰的方法|
|xiezijing|定义生物蝎子精|有关蝎子精的方法|



### 关键代码简要介绍：
Field：
public synchronized boolean meet_ornot(int nx, int ny) ：判断某个位置是否已经有生物，即判是否会和其他生物相遇，若那个位子已经有生物占据，则不能往那个位置走。
public boolean judgement_enemy(Creature move_thing, int nx, int ny) ：判断自己的下一个位置是否有enemy，若有则进行fighting。

public void fighting(Creature x1, Creature x2)：fighting函数，用一个随机数从0-10，当随机数取0-6时，葫芦娃和爷爷获胜，而取7-10时，蝎子精和蛇精获胜。邪不胜正。
### 测试方法介绍
 test测试方法测试的是Field里面的judge_enemy函数，用来判断该生物的前方是否有敌人。用两个正反案例进行测试，即：用有敌人和无敌人的情况测试该函数的返回值。
