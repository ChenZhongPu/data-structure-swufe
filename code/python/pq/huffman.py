import heapq


class HuffmanNode:
    def __init__(self, char, freq):
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

    def __lt__(self, other):
        return self.freq < other.freq


def build_frequency_dict(text):
    """Create a frequency dictionary for characters in the text."""
    freq_dict = {}
    for char in text:
        freq_dict[char] = freq_dict.get(char, 0) + 1
    return freq_dict


def build_huffman_tree(freq_dict):
    """Build Huffman tree using heapq for efficient selection."""
    heap = [HuffmanNode(char, freq) for char, freq in freq_dict.items()]
    heapq.heapify(heap)

    while len(heap) > 1:
        left = heapq.heappop(heap)
        right = heapq.heappop(heap)

        parent = HuffmanNode(None, left.freq + right.freq)
        parent.left = left
        parent.right = right

        heapq.heappush(heap, parent)

    return heap[0] if heap else None


def generate_huffman_codes(node, current_code="", codes=None):
    """Generate Huffman codes for each character."""
    if codes is None:
        codes = {}

    if node.char is not None:
        codes[node.char] = current_code
        return codes

    if node.left:
        generate_huffman_codes(node.left, current_code + "0", codes)
    if node.right:
        generate_huffman_codes(node.right, current_code + "1", codes)

    return codes


def huffman_encode(text):
    """Encode text using Huffman coding."""
    freq_dict = build_frequency_dict(text)

    huffman_tree = build_huffman_tree(freq_dict)

    huffman_codes = generate_huffman_codes(huffman_tree)

    encoded_text = "".join(huffman_codes[char] for char in text)

    return {
        "encoded_text": encoded_text,
        "huffman_codes": huffman_codes,
        "tree": huffman_tree,
    }


def main():
    text = "hello world"
    result = huffman_encode(text)

    print("Original Text:", text)
    print("Huffman Codes:", result["huffman_codes"])
    print("Encoded Text:", result["encoded_text"])


if __name__ == "__main__":
    main()
