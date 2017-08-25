package com.hash.simple;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashRouter{
	
	public static void main(String[] args) {
		List<PhysicalNode> pNodes = new ArrayList<>();
		pNodes.add(new PhysicalNode("www.1.com", "192.168.2.201", 3000));
		pNodes.add(new PhysicalNode("www.2.com", "192.168.2.202", 3000));
		pNodes.add(new PhysicalNode("www.3.com", "192.168.2.203", 3000));
		ConsistentHashRouter hashRouter = new ConsistentHashRouter(pNodes, 3);
		
		hashRouter.getNode("ok");
		
		
	}
	
	/**
	 * 一致性Hash环
	 */
	private SortedMap<Long,VirtualNode> ring = new TreeMap<Long,VirtualNode>();	
	
	/**
	 * Md5Hash工具
	 */
	private MD5Hash hashfunction = new MD5Hash();	

	public ConsistentHashRouter(Collection<PhysicalNode> pNodes, int vnodeCount) {
		for (PhysicalNode pNode : pNodes) {
			addNode(pNode,vnodeCount);
		}
	}
	
	/**
	 * 往一致性Hash环中添加物理节点，并且指定物理节点对应的虚拟节点的个数
	 * @param pNode
	 * @param vnodeCount
	 */
	public void addNode(PhysicalNode pNode, int vnodeCount){
		int existingReplicas = getReplicas(pNode.toString());
		for(int i=0;i<vnodeCount;i++){
			VirtualNode vNode= new VirtualNode(pNode,i+existingReplicas);
			ring.put(hashfunction.hash(vNode.toString()), vNode);
		}
	}
	
	/**
	 * 删除一致性Hash环中的一个物理节点
	 * @param pNode
	 */
    public void removeNode(PhysicalNode pNode) {
        Iterator<Long> it = ring.keySet().iterator();
        while (it.hasNext()) {
            Long key = it.next();
            VirtualNode virtualNode = ring.get(key);
            if (virtualNode.matches(pNode.toString())) {
                it.remove();
            }
        }
    }
    
    /**
     * 根据key从一致性Hash环中顺时针取出最近的虚拟节点，并返回对应的物理节点
     * @param key
     * @return
     */
    public PhysicalNode getNode(String key){
        if (ring.isEmpty()) {
            return null;
        }
    	Long hashKey=hashfunction.hash(key);
    	//得到TreeMap中key比hashKey大的子Map
    	SortedMap<Long,VirtualNode> tailMap= ring.tailMap(hashKey);
    	//从子Map中取出最小的HashKey，相当于一致性hash算法顺时针找虚拟节点
		hashKey = tailMap!=null&&!tailMap.isEmpty() ?tailMap.firstKey():ring.firstKey();
		//拿到虚拟节点对应的物理节点
		return ring.get(hashKey).getParent();	
    }
	
    /**
     * 当前节点中已经存在的虚拟节点的个数
     * @param nodeName
     * @return
     */
    public int getReplicas(String nodeName) {
        int replicas = 0;
        for (VirtualNode node : ring.values()) {
            if (node.matches(nodeName)) {
                replicas++;
            }
        }
        return replicas;
    }
    
	private static class MD5Hash {
		MessageDigest instance;

		public MD5Hash() {
			try {
				instance = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
			}
		}
		
		long hash(String key) {
			instance.reset();
			instance.update(key.getBytes());
			byte[] digest = instance.digest();

			long h = 0;
			for (int i = 0; i < 4; i++) {
				h <<= 8;
				h |= ((int) digest[i]) & 0xFF;
			}
			return h;
		}
	};
}
