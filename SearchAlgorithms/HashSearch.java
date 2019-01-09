/**** 
 * Hash查找 
 *  
 * @param hash 
 * @param hashLength 
 * @param key 
 * @return 
 */  
public static int hashSearch(int[] hash, int hashLength, int key) {  
    // hash method 
    int hashAddress = key % hashLength;  
  
    // 指定hashAdrress对应值存在但不是关键值，则用开放寻址法解决  
    while (hash[hashAddress] != 0 && hash[hashAddress] != key) {  
        hashAddress = (++hashAddress) % hashLength;  
    }  
  
    // 查找到了开放单元，表示查找失败  
    if (hash[hashAddress] == 0)  
        return -1;  
    return hashAddress;  
  
}  
  
/*** 
 * 数据插入Hash表 
 *  
 * @param hash 
 * @param hashLength 
 * @param data 
 */  
public static void insert(int[] hash, int hashLength, int data) {  
    // 哈希函数  
    int hashAddress = data % hashLength;  
  
    // 如果key存在，则说明已经被别人占用，此时必须解决冲突  
    while (hash[hashAddress] != 0) {  
        // 用开放寻址法找到  
        hashAddress = (++hashAddress) % hashLength;  
    }  
  
    // 将data存入字典中  
    hash[hashAddress] = data;  
}  