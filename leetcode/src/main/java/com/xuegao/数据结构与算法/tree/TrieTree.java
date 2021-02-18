package com.xuegao.数据结构与算法.tree;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.tree
 * <br/> @ClassName：TrieTree
 * <br/> @Description：前缀树
 * <br/> @author：80004960
 * <br/> @date：2020/8/19 14:12
 */
public class TrieTree {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("ac");
        trie.add("abc");
        trie.add("acb");
        trie.add("abcc");
        trie.add("aab");
        trie.add("abx");
        trie.add("abc");

        System.out.println(trie.get("abc"));
        System.out.println(trie.getPre("ab"));
    }
}

/**
 * path表示字符路过这个结点的次数（即表示存在以当前结点为前缀的字符有多少个）；
 * end记录以当前结点为结束的字符有多少个。
 */
class TrieNode {
    public int path;
    public int end;
    public TrieNode[] nexts;

    public TrieNode() {
        path = 0;
        end = 0;
        //只能存英文小写字母，如果是ASCII码可以生成256大小的数组
        //如果想存更多种类的字符可以改为map结构
        nexts = new TrieNode[26];
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    /**
     * 字典树的加入过程
     */
    public void add(String word) {
        if (word == null) return;
        char[] chars = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.path++;
        }
        node.end++;
    }

    /**
     * 字典树查询目标单词出现的次数
     */
    public int get(String word) {
        if (word == null) return 0;
        char[] chars = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null) return 0;
            node = node.nexts[index];
        }
        return node.end;
    }

    /**
     * 字典树查询以目标前缀的单词有多少个
     */
    public int getPre(String word) {
        if (word == null) return 0;
        char[] chars = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null)
                return 0;
            node = node.nexts[index];
        }
        return node.path;
    }
}
