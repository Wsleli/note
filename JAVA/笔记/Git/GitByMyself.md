# 命令行-- git基本操作

##环境配置

当安装Git后首先要做的事情是设置用户名称和email地址。这是非常重要的，因为每次Git提交都会使用该用户信息

```shell
#设置用户信息 
    git config --global user.name "wsleli"
    git config --global user.email "aiyingggg@gmail.com"
#查看配置信息
    git config --list
    git config user.name
    git config user.email
#通过上面的命令设置的信息会保存在~/.gitconfig文件中
#q 退出
#查看日志
	git log
```

##初始化本地仓库 init

```shell
# 初始化仓库带工作区
git init
# 初始化仓库不带工作区
git init --bare  
```

##克隆 clone

```shell
# 从远程仓库克隆
git clone 远程Git仓库地址 
例如: git clone https://gitee.com/itcast/gittest.git
```

##查看状态 status

```shell
# 查看状态
git status 
#查看状态 使输出信息更加简洁
git status –s 
```

##add 

```shell
# 将未跟踪的文件加入暂存区
git add  <文件名>  
# 将暂存区的文件取消暂存 (取消 add )
git reset  <文件名>  
```

##commit

```shell
# git commit 将暂存区的文件修改提交到本地仓库
git commit -m "日志信息"  <文件名>  
```

##删除 rm

```shell
# 从本地工作区 删除文件
git rm <文件名>  
# 如果本工作区库误删, 想要回退
git checkout head <文件名>  
```

# 命令行--git 远程仓库操作

## 查看远程 

```shell
# 查看远程  列出指定的每一个远程服务器的简写
git remote 
# 查看远程 , 列出 简称和地址
git remote  -v  
# 查看远程仓库详细地址
git remote show  <仓库简称>
```

## 添加/移除远测仓库（绑定的链接）

```shell
# 添加远程仓库
git remote add <shortname> <url>
# 移除远程仓库和本地仓库的关系(只是从本地移除远程仓库的关联关系，并不会真正影响到远程仓库)
git remote rm <shortname> 
```

## 从远程仓库获取代码

```shell
# 从远程仓库克隆
git clone <url> 
# 从远程仓库拉取 (拉取到.git 目录,不会合并到工作区,工作区发生变化)
git fetch  <shortname>  <分支名称>
# 手动合并  把某个版本的某个分支合并到当前工作区
git merge <shortname>/<分支名称>
# 从远程仓库拉取 (拉取到.git 目录,合并到工作区,工作区不发生变化) = fetch+merge
git pull  <shortname>  <分支名称>
git pull  <shortname>  <分支名称>  --allow-unrelated-histories  #  强制拉取合并
```

注意：如果当前本地仓库不是从远程仓库克隆，而是本地创建的仓库，并且仓库中存在文件，此时再从远程仓库拉取文件的时候会报错（fatal: refusing to merge unrelated histories ），解决此问题可以在git pull命令后加入参数--allow-unrelated-histories (如上 命令)

```shell
# 将本地仓库推送至远程仓库的某个分支
git push [remote-name] [branch-name]
```

# 命令行-- 分支

```shell
# 默认 分支名称为 master
# 列出所有本地分支
git branch
# 列出所有远程分支
git branch -r
# 列出所有本地分支和远程分支
git branch -a
# 创建分支
git branch <分支名>
# 切换分支 
git checkout <分支名>
# 删除分支(如果分支已经修改过,则不允许删除)
git branch -d  <分支名>
# 强制删除分支
git branch -D  <分支名>
```

```shell
# 提交分支至远程仓库
git push <仓库简称> <分支名称>	
# 合并分支 将其他分支合并至当前工作区
git merge <分支名称>
# 删除远程仓库分支
git push origin –d branchName
```

# 命令行 --tag（标签）

```shell
# 列出所有tag
git tag
# 查看tag详细信息 
git show [tagName]
# 新建一个tag
git tag [tagName]
# 提交指定tag
$ git push [仓库简称] [tagName]
# 新建一个分支，指向某个tag
$ git checkout -b [branchName] [tagName]
# 删除本地tag
$ git tag -d [tagName]
# 删除远程tag (注意 空格)
$ git push origin :refs/tags/[tagName]
```

# 忽略文件

常见格式

```yaml
# 所有以.a 结尾的文件讲被忽略(递归)
*.a
# 不管其他规则怎样,强制不忽略  lib.a
!lib.a
# 只忽略 文件 TODO (注意这里是文件)
/TODO
# 忽略 build文件夹下所有内容(递归) 这里是文件夹
build/
# 忽略 doc 目录下以 *.txt 结尾的文件 (不递归)
doc/*.txt
# 忽略 doc 目录下以 *.pdf 结尾的文件 (递归)
doc/**/*.pdf
```

当然理解了上述规则,也可以手动编辑该文件,而不用通过窗口化操作

# 生成公钥私钥

```shell
ssh-keygen -t rsa
```

 一直回车即可

 会默认用户目录 .ssh 目录生成一个默认的id_rsa（私钥）文件 和id_rsa.pub（公钥）

TortoiseGit可视化工具需要配置网络，路径为`*\Git\usr\bin\ssh.exe`

git站点配置公钥