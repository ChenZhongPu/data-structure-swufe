from manim import *


class AVLInsertion(Scene):
    def create_node(self, value, position):
        """Helper function to create a node with centered text"""
        node = Circle(radius=0.5)
        text = Text(str(value), font_size=24)
        node.move_to(position)
        text.move_to(node.get_center())
        return VGroup(node, text)

    def construct(self):
        # Title
        title = Text("AVL Tree Insertions: 1 → 2 → 6", font_size=36)
        title.to_edge(UP)
        self.play(Write(title))

        # Step 1: Insert 1
        step1 = Text("Step 1: Insert 1", font_size=24)
        step1.next_to(title, DOWN)
        self.play(Write(step1))

        # Create first node (1)
        node_1_group = self.create_node("1", [0, 2, 0])
        self.play(Create(node_1_group))
        self.wait(1)

        # Step 2: Insert 2
        step2 = Text("Step 2: Insert 2", font_size=24)
        step2.move_to(step1.get_center())
        self.play(FadeOut(step1), Write(step2))

        # Add node 2
        node_2_group = self.create_node("2", [1.5, 0.5, 0])
        edge_1_2 = Line(
            start=node_1_group[0].get_center() + DOWN * 0.5,
            end=node_2_group[0].get_center() + UP * 0.5,
        )
        self.play(Create(node_2_group), Create(edge_1_2))
        self.wait(1)

        # Step 3: Insert 6
        step3 = Text("Step 3: Insert 6", font_size=24)
        step3.move_to(step2.get_center())
        self.play(FadeOut(step2), Write(step3))

        # Add node 6
        node_6_group = self.create_node("6", [2.5, -1, 0])
        edge_2_6 = Line(
            start=node_2_group[0].get_center() + DOWN * 0.5,
            end=node_6_group[0].get_center() + UP * 0.5,
        )
        self.play(Create(node_6_group), Create(edge_2_6))

        # Show imbalance
        imbalance_text = Text("Right subtree too heavy!", font_size=24, color=RED)
        imbalance_text.to_edge(RIGHT)
        self.play(Write(imbalance_text))
        self.wait(1)

        # Rotation notification
        rotation_text = Text("Performing left rotation", font_size=24, color=GREEN)
        rotation_text.to_edge(RIGHT)
        self.play(FadeOut(imbalance_text), Write(rotation_text))

        # Left rotation
        self.play(
            # Move node 2 to root position
            node_2_group.animate.move_to([0, 2, 0]),
            # Move node 1 to left child
            node_1_group.animate.move_to([-1.5, 0.5, 0]),
            # Move node 6 to right child
            node_6_group.animate.move_to([1.5, 0.5, 0]),
            # Update edges
            Transform(
                edge_1_2,
                Line(
                    start=[0, 2, 0] + DOWN * 0.5 + LEFT * 0.5,
                    end=[-1.5, 0.5, 0] + UP * 0.5,
                ),
            ),
            Transform(
                edge_2_6,
                Line(
                    start=[0, 2, 0] + DOWN * 0.5 + RIGHT * 0.5,
                    end=[1.5, 0.5, 0] + UP * 0.5,
                ),
            ),
            run_time=3,
        )
        self.wait(2)


if __name__ == "__main__":
    with tempconfig({"preview": True}):
        scene = AVLInsertion()
        scene.render()
