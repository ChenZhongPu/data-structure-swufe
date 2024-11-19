class HuffmanNode:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

def build_frequency_dict(text):
    """Create a frequency dictionary for characters in the text."""
    freq_dict = {}
    for char in text:
        freq_dict[char] = freq_dict.get(char, 0) + 1
    return freq_dict

def build_huffman_tree(freq_dict):
    """Build Huffman tree using naive sorting approach."""
    # Convert frequency dict to list of nodes
    nodes = [HuffmanNode(char, freq) for char, freq in freq_dict.items()]
    
    # Naive approach: Sort nodes by frequency each iteration
    while len(nodes) > 1:
        # Sort nodes by frequency in ascending order
        nodes.sort(key=lambda x: x.freq)
        
        # Take two lowest frequency nodes
        left = nodes.pop(0)
        right = nodes.pop(0)
        
        # Create parent node
        parent = HuffmanNode(None, left.freq + right.freq)
        parent.left = left
        parent.right = right
        
        # Add parent back to list of nodes
        nodes.append(parent)
    
    # Return the root of the Huffman tree
    return nodes[0] if nodes else None

def generate_huffman_codes(node, current_code='', codes=None):
    """Generate Huffman codes for each character."""
    if codes is None:
        codes = {}
    
    # Leaf node (character node)
    if node.char is not None:
        codes[node.char] = current_code
        return codes
    
    # Recursively traverse left and right subtrees
    if node.left:
        generate_huffman_codes(node.left, current_code + '0', codes)
    if node.right:
        generate_huffman_codes(node.right, current_code + '1', codes)
    
    return codes

def huffman_encode(text):
    """Encode text using Huffman coding."""
    # Build frequency dictionary
    freq_dict = build_frequency_dict(text)
    
    # Build Huffman tree
    huffman_tree = build_huffman_tree(freq_dict)
    
    # Generate Huffman codes
    huffman_codes = generate_huffman_codes(huffman_tree)
    
    # Encode the text
    encoded_text = ''.join(huffman_codes[char] for char in text)
    
    return {
        'encoded_text': encoded_text,
        'huffman_codes': huffman_codes,
        'tree': huffman_tree
    }

# Example usage
def main():
    text = "hello world"
    result = huffman_encode(text)
    
    print("Original Text:", text)
    print("Huffman Codes:", result['huffman_codes'])
    print("Encoded Text:", result['encoded_text'])

if __name__ == "__main__":
    main()