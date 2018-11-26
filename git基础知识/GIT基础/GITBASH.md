
# Git bash 常用指令

现在很多公司和开发人员都在使用分布式版本控制系统git。
但是大部分人员在使用中都是使用的第三方插件来进行git操作。
dly我推荐大家使用git bash来操作。这样方便快捷，也有助于你从根本上知道git原理。
而且可以以不变应万变，比如a同学代码库出现了问题如冲突等问题需要你来support下。
但是a同学可能是用的macOS系统，用的是一个你没使用过的三方git客户端工具，你可能无法施展自己的才能。
但是你打开终端一同git bash操作就可以快速轻松的帮他解决问题。当然，使用git bash 你需要记住很多指令。


git怎样删除未监视的文件untracked files
```git clean -f```

 
连 untracked 的目录也一起删掉
```git clean -fd```
 
连 gitignore 的untrack 文件/目录也一起删掉 （慎用，一般这个是用来删掉编译出来的 .o之类的文件用的）
```git clean -xfd```
 
在用上述 git clean 前，墙裂建议加上 -n 参数来先看看会删掉哪些文件，防止重要文件被误删
```git clean -nxfd```
```git clean -nf```
```git clean -nfd```

git撤销已commit，但未push的提交
    1、找到之前提交的git commit的id 
    ```git log ```
    2、找到想要撤销的id 
        完成撤销,同时将代码恢复到前一commit_id 对应的版本
        ```git reset –hard id```
        完成Commit命令的撤销，但是不对代码修改进行撤销，可以直接通过git commit 重新提交对本地代码的修改
        ```git reset id```
    

切换到要操作的项目文件夹 
```cd <ProjectPath>```

删除本地分支 
```git branch -d <BranchName>```

强制删除本地分支 
```git branch -D <BranchName>```

查看项目的分支们(包括本地和远程) 
```git branch -a```

删除远程分支 
```git push origin --delete <BranchName>```

本地所有修改的。没有的提交的，都返回到原来的状态
```git checkout .```

把所有没有提交的修改暂存到stash里面。可用git stash pop恢复。
```git stash```

取消暂存
```git stash apply```


返回到某个节点，不保留修改。
```git reset --hard HASH```

返回到某个节点。保留修改
```git reset --soft HASH```

GiTHUB SSH Keys 一般位置
```C:\Users\Administrator\.ssh\id_rsa.pub```

放弃本地某个文件的修改，或所有修改
```git checkout 文件名```

查看当前分支版本号
```git rev-parse HEAD```

查看某分支提交记录
```git log --pretty branchName```

gitlab上创建一个新的远程分支以后，要先用一下命令来fetch一下该分支： 
```git fetch origin 新建的分支名字```
然后拉取远程分支并创建本地分支
```git checkout -b 本地分支名x origin/远程分支名x```

git删除/撤销已经push到程服务器上某次代码提交
```git reset --hard 126f206185f225879f2723ca421f4dee44ca8fe7```
```git push origin HEAD --force```

查看远程仓库的地址
```git remote -v```

切换远程仓库地址：
    方式一：修改远程仓库地址
        【git remote set-url origin URL】 更换远程仓库地址，URL为新地址。
    方式二：先删除远程仓库地址，然后再添加
        【git remote rm origin】 删除现有远程仓库 
        【git remote add origin url】添加新远程仓库
        
基于xx分支新建本地分支并切换到新分支
    checkout -b newBanrch
    
# 未完待续

