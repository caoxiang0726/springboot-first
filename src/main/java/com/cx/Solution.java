package com.cx;

class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.toUpperCase().equals(word)){
            return true;
        }
        else if(word.toLowerCase().equals(word)){
            return true;
        }else if(Character.isUpperCase(word.charAt(0)) 
                 && (word.substring(1).equals(word.substring(1).toLowerCase()))){
            return true;
            
        }
        
        
        return false;
        
        
        
    }
}